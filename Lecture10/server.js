"use strict";

const express = require("express");
const path = require("path");
const expressHbs = require("express-handlebars");

const app = express();
app.use(express.urlencoded({extended: true}));

app.engine("handlebars", expressHbs());
app.set("view engine", "handlebars");


const users = [];
let id = 1;


app.get("/", (req, res) => {
    res.render("home", {
        message: "Hello world!",
    });
});

app.get("/test", (req, res) => {
    res.sendFile(path.join(__dirname, "/test.html"));
});

app.get("/csv", (req, res) => {
    res.sendFile(path.join(__dirname, "/test.csv"));
});

app.get("/params", (req, res) => {
    res.end("Hello world! ID from query: " + req.query.id);
});

app.get("/params/:id", (req, res) => {
    res.end("Hello world! ID from params: " + req.params.id);
});

app.get("/users/all", (req, res) => {
    res.send(users);
});

app.get("/users/show", (req, res) => {
    res.render("users", {
        users: users
    });
});

app.get("/users/insert", (req, res) => {
    res.render("insert");
});

app.post("/users/insert", (req, res) => {
    const email = req.body.email;
    const name = req.body.name;
    const age = req.body.age;
    const user = {
        "id": id++,
        "email": email,
        "name": name,
        "age": age
    };
    users.push(user);
    res.send(user);
});

const deleteUser = (id) => {
    for (let i = 0; i < users.length; i++) {
        if (users[i].id.toString() === id) {
            users.splice(i, 1);
            i--;
        }
    }
};

app.get("/users/delete/:id", (req, res) => {
    deleteUser(req.params.id);
    res.sendStatus(200);
});

app.delete("/users/delete/:id", (req, res) => {
    deleteUser(req.params.id);
    res.sendStatus(200);
});

app.listen(3000);
console.log("Server started at http://localhost:3000");
