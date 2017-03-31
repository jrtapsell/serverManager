ports = [];
ws = new WebSocket("ws://localhost:8080/ws/");
ws.onmessage = function (data) {
    var contained = JSON.parse(data.data);
    console.log("Message", contained);
    ports.forEach(function(element) {
        element.postMessage(contained);
    });
};
self.addEventListener("connect", function (e) {
    var port = e.ports[0];
    console.log(e.ports, ports);
    port.addEventListener("message", function (e) {
        ports.forEach(function(element) {
            element.postMessage(e);
        });
    }, false);
    ports.push(port);
    port.start();
});