package handlers

import (
	"net/http"
	"net/http/httptest"
	"testing"
	"time"
)

func TestRateLimiter_ExcedeLimite(t *testing.T) {
	// limpa estado global
	clients = make(map[string]*Client)

	handler := RateLimitter(http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
		w.WriteHeader(http.StatusOK)
	}))

	ip := "192.168.0.1:1234"

	var rr *httptest.ResponseRecorder

	for i := 0; i < 6; i++ {
		req := httptest.NewRequest("GET", "/api", nil)
		req.RemoteAddr = ip

		rr = httptest.NewRecorder()
		handler.ServeHTTP(rr, req)
	}

	// valida status
	if rr.Code != http.StatusTooManyRequests {
		t.Fatalf("esperado 429, recebeu %d", rr.Code)
	}

	// valida mensagem
	body := rr.Body.String()
	expected := "Rate limit excedido\n"

	if body != expected {
		t.Fatalf("esperado '%s', recebeu '%s'", expected, body)
	}
}

func TestRateLimiter_Bloqueado(t *testing.T) {
	clients = make(map[string]*Client)

	ip := "192.168.0.1"

	// força estado bloqueado
	clients[ip] = &Client{
		Count:        10,
		LastSeen:     time.Now().Unix(),
		BlockedUntil: time.Now().Add(1 * time.Hour).Unix(),
	}

	handler := RateLimitter(http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
		w.WriteHeader(http.StatusOK)
	}))

	req := httptest.NewRequest("GET", "/api", nil)
	req.RemoteAddr = ip + ":1234"

	rr := httptest.NewRecorder()
	handler.ServeHTTP(rr, req)

	if rr.Code != http.StatusTooManyRequests {
		t.Fatalf("esperado 429, recebeu %d", rr.Code)
	}

	body := rr.Body.String()
	expected := "Tente novamente em 1 hora\n"

	if body != expected {
		t.Fatalf("esperado '%s', recebeu '%s'", expected, body)
	}
}
