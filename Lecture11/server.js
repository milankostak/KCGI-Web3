"use strict";

const express = require("express");
const path = require("path");
const ws = require("ws");

const app = express();

app.get("/", (req, res) => {
    res.sendFile(path.join(__dirname, "/index.html"));
});

app.get("/ws", (req, res) => {
    res.sendFile(path.join(__dirname, "/ws.html"));
});

app.get("/ajax", (req, res) => {
    const user = {
        "email": "test@example.com",
        "name": "Satoshi Nakamoto",
        "age": parseInt((Math.random() * 100).toFixed(0))
    };
    res.send(JSON.stringify(user));
});

app.get("/ajax-random", (req, res) => {
    res.send(Math.random().toString());
});

app.listen(3000);
console.log("Server started at http://localhost:3000");

const wsServer = new ws.Server({
    port: 4000
});

let wsClients = [];
wsServer.on("connection", socket => {
    wsClients.push(socket);

    // When you receive a message, send that message to every socket.
    socket.on("message", msg => {
        console.log("Received:", msg);
        wsClients.forEach(client => client.send(msg));
    });

    // When a socket closes, or disconnects, remove it from the array.
    socket.on("close", () => {
        wsClients = wsClients.filter(s => s !== socket);
    });
});

console.log("WS server started at ws://localhost:4000");
