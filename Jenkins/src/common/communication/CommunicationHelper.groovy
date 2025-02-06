package common 

// Base class for all communication channels
abstract class CommunicationHelper {

    // Abstract method to send the message, which must be implemented by subclasses
    abstract void sendMessage(String status, String message)

}
