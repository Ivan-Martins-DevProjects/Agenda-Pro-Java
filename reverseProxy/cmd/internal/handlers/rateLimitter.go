package handlers

import (
	"net"
	"net/http"
	"strings"
	"sync"
	"time"
)

type Client struct {
	Count        int
	LastSeen     int64
	BlockedUntil int64
}

var clients = make(map[string]*Client)
var mu sync.Mutex

func CleanClients() {
	ticker := time.NewTicker(5 * time.Minute)

	go func() {
		for {
			select {
			case <-ticker.C:
				mu.Lock()

				now := time.Now().Unix()
				for ip, client := range clients {
					if now > client.BlockedUntil {
						delete(clients, ip)
						mu.Unlock()
					}
				}
			}
		}
	}()
}

func RateLimitter(next http.Handler) http.Handler {
	return http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
		ip := getClientIp(r)
		now := time.Now().Unix()
		blocked := time.Now().Add(1 * time.Hour).Unix()

		// Trava a variável para evitar problemas de concorrência
		mu.Lock()

		// Verifica se ja existe esse ip salvo
		client, exists := clients[ip]

		// Caso não exista adiciona ao map pela primeira vez
		if !exists {
			clients[ip] = &Client{
				Count:    1,
				LastSeen: now,
			}
			mu.Unlock()
			next.ServeHTTP(w, r)
			return
		}

		// Se a ultima vez que interagiu foi a mais de 60 segundos atrás zera a contagem
		if now-client.LastSeen > 60 {
			client.Count = 1
			client.LastSeen = now
			mu.Unlock()
			next.ServeHTTP(w, r)
			return
		}

		// Adiciona mais 1 na contagem caso nenhuma condição anterior tenha sido atingida
		client.Count++

		// Caso o cliente esteja blockeado retorna uma mensagem de erro
		if now < client.BlockedUntil {
			mu.Unlock()
			http.Error(w, "Tente novamente em 1 hora", http.StatusTooManyRequests)
			return
		}

		// Se tiver mais de 5 interações retorna o erro de TimeLimmits e bloqueia o cliente
		if client.Count > 5 {
			client.BlockedUntil = blocked
			mu.Unlock()
			http.Error(w, "Rate limit excedido", http.StatusTooManyRequests)
			return
		}

		mu.Unlock()
		next.ServeHTTP(w, r)
	})
}

func getClientIp(r *http.Request) string {
	ip := r.Header.Get("X-Forwarded-For")
	if ip != "" {
		return strings.Split(ip, ",")[0]
	}

	ip, _, _ = net.SplitHostPort(r.RemoteAddr)
	return ip
}
