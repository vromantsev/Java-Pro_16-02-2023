package ua.hillel.web.sockets;

public class WebDemo {

    // Internet, web - computers that are connected to a single web and are able
    // to speak to each other via remote requests

    // how server understands the requests and which one to process and which one do not?
    // HTTP/HTTPS (secured) - Hyper Text Transfer Protocol
    // Client-server protocol (http code 200 - OK, 201 - created)
    // Headers request/response, payload - body
    // headers, payload - picture (.jpg), text message, order (json, xml)

    // IP - Internet Protocol (WWW) -> exchange info (information is provided as packages)
    // with another computer, --> IPv4, IPv6 IP-address 192.10.32.123 (mvnrepository.com)
    // port-forwarding (whitelisting)

    // SSH - secure shell protocol -> specific users, username/password
    // FTP - file transfer protocol
    // POP3, IMAP vr@gmail.com

    // Serialization - object is transformed to the byte array
    // Deserialization - transform array of bytes to object
    // Serializable
    // What problem is solves? Data exchange across the globe/web.

    // serialization -> send -> deserialization

    // Let's assume you have web-app, and you/clients are trying to understand how it works:
    // 1. High level abstraction -> HTTP -> 2. Socket (client side) , ServerSocket (server side)
    /*
    * Content-Type: application/json
    * Content-Length: 14
    * Method: POST
    *
    * Body: {your object as json}
    *
    * JSON, XML
    * */
}
