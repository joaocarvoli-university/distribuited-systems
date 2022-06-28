import json
import skeletons.Weather as sk_weather
import skeletons.CurrencyConverter as sk_currency


class Dispatcher:
    def __init__(self):
        print("do nothing")

    def invoke(message: json) -> bool:
        """
        This method is responsible to select which skeleton of a specific service will be used
        :param message: The client message send from the client
        :return: the response message
        """

        if message['serviceName'] == 'CurrencyConverter':
            del message['serviceName']
            if message['methodName'] == 'convert':
                del message['methodName']
                sk_currency.CurrencyConverter.convert(message)
            elif message['methodName'] == 'currency_exists':
                del message['methodName']
                sk_currency.CurrencyConverter.currency_exists(message)
            elif message['methodName'] == 'currencies_available':
                del message['methodName']
                sk_currency.CurrencyConverter.currencies_available(message)

        elif message['serviceName'] == 'WeatherAPI':
            del message['serviceName']  # Reducing message size

        return False
