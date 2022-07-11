# Network Transparency  
In our solution, the objective is to provide that the user would be to access the methods of any services as if he accessed locally. The communication will be based on request-response.

How the server and client will be implemented in UDP protocol we need to guarantee the below requirements:
- Retransmission
- Removing duplicated messages.

## Let's explain the main components of our architecture:
### Client side
- Client: Interacts to the user, receives input data and return output data as like a GUI or terminal
- Proxy: Interacts to the client, make the remote call transparent
- UDPClient: Send a byte set into the server configured previously, send requests and receive responses
### Server side
- UDPServer: Receive a byte set from the client, receive requests and send responses, and call the dispacther
- Dispatcher: Select the service based on message params and call it
- Skeleton: Receives the message, treat the message preparing the params to call a specific method from the service

## Diagrams
### This diagram represents the flow based on a generic service

<img src="/docs/Generic%20Diagram.drawio.svg" alt="drawing" width="600"/>

#### The model of the diagram based on our services are here: [Diagrams](/services/README.md)

