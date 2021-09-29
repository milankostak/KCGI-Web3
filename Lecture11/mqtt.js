const mqtt = require("mqtt");
const client = mqtt.connect("mqtt://test.mosquitto.org");

client.on("connect", () => {
    client.subscribe("presence", () => {
        client.publish("presence", "Hello mqtt");
    });
    client.subscribe(["home/#", "house/#"]);
});

client.on("message", (topic, message) => {
    console.log("Topic:", topic);
    console.log("Message:", message.toString());
});