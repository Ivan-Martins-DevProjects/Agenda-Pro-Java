package handlers

import (
	"encoding/json"
	"net/http"
)

func RedirectRequest(w http.ResponseWriter, r *http.Request) {
	var body any

	err := json.NewDecoder(r.Body).Decode(&body)
	if err != nil {
		http.Error(w, "Requisição inválida", http.StatusBadRequest)
		return
	}

	w.Header().Set("Content-Type", "application/json")
	json.NewEncoder(w).Encode(body)
}
