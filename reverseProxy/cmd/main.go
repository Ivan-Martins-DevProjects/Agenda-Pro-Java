package main

import (
	"log"
	"net/http"

	"github.com/Ivan-Martins-DevProjects/cmd/internal/handlers"
)

func main() {
	mux := http.NewServeMux()
	go handlers.CleanClients()

	mux.Handle("/api", handlers.RateLimitter(http.HandlerFunc(handlers.RedirectRequest)))

	server := &http.Server{
		Addr:    ":8282",
		Handler: mux,
	}

	log.Printf("Servidor rodando em http://localhost:%s", server.Addr)
	log.Fatal(server.ListenAndServe())
}
