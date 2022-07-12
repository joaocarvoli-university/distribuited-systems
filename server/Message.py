from uuid import uuid4
import json


class Message:
    def __init__(self, messageType="", serviceName="", methodName="", arguments=""):
        self._messageType = messageType
        self._requestId = str(uuid4())
        self._serviceName = serviceName
        self._methodName = methodName
        self._arguments = arguments
        #self._response = None
        #self._errorMessage = None

    def deserialize(self) -> dict:
        return self.__dict__

    def serialize(self, message: json) -> None:
        self.__dict__ = message

    def get_message_type(self):
        return self.messageType

    def set_message_type(self, type: int):
        self.messageType = type

    def get_request_id(self):
        return self.requestId

    def get_service_name(self):
        return self.serviceName

    def get_method_name(self):
        return self.methodName

    def get_arguments(self):
        return self.arguments

    def set_arguments(self, arguments):
        self.arguments = arguments

#    def set_response(self, response: float):
#        self.response = response
#
#    def set_error_message(self, error: dict):
#        self.errorMessage = error["message"]

    # def delete_field(self, field: str):
    #    self.__dict__.pop(field)
