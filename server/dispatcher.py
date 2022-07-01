import json
import skeletons.Weather as sk_weather
import skeletons.CurrencyConverter as sk_currency


class Dispatcher:
    def __init__(self):
        print("do nothing")

    def invoke(self, message: json) -> json:
        """
        This method is responsible to select which skeleton of a specific service will be used
        :param message: The client message send from the client
        :return: the response message
        """
        sk = sk_currency.CurrencyConverter()

        if message['serviceName'] == 'CurrencyConverter':
            del message['serviceName']
            if message['methodName'] == 'convert':
                del message['methodName']
                message = sk.convert(message)
            elif message['methodName'] == 'currency_exists':
                del message['methodName']
                message = sk.currency_exists(message)
            elif message['methodName'] == 'currencies_available':
                del message['methodName']
                message = sk.currencies_available(message)

        elif message['serviceName'] == 'WeatherAPI':
            del message['serviceName']
            if message['methodName'] == 'get_weather_clouds':
                del message['methodName']
                sk_weather.Weather.get_weather_clouds(message)
            elif message['methodName'] == 'get_weather_temperature':
                del message['methodName']
                sk_weather.Weather.get_weather_temperature(message)
            elif message['methodName'] == 'get_weather_wind':
                del message['methodName']
                sk_weather.Weather.get_weather_wind(message)

        return message
