FROM golang:1.26

WORKDIR /app

# instala o air
RUN go install github.com/air-verse/air@latest

COPY go.mod ./
RUN go mod download

COPY . .

CMD ["air"]
