import json
from Message import Message
import skeletons.Weather as SkWeather
import skeletons.CurrencyConverter as SkCurrency


class Dispatcher:
    def invoke(self, message: Message) -> json:
        """
        This method is responsible to select which skeleton of a specific service will be used
        :param message: The client message send from the client
        :return: the response message
        """

        sk_cc = SkCurrency.CurrencyConverter()
        sk_wt = SkWeather.Weather()

        if message.get_service_name() == 'CurrencyConverter':
            if message.get_method_name() == 'convert':
                response = sk_cc.convert(message.get_arguments())
                message.set_message_type(1)
                message.set_arguments(response)

            elif message.get_method_name() == 'currency_exists':
                response = sk_cc.currency_exists(message.get_arguments())
                message.set_message_type(1)
                message.set_arguments(response)

            elif message.get_method_name() == 'currencies_available':
                response = sk_cc.currencies_available()
                message.set_message_type(1)
                message.set_arguments(response)

        elif message.get_service_name() == 'WeatherAPI':
            if message.get_method_name() == 'get_weather_clouds':
                response = sk_wt.get_weather_clouds(message.get_arguments())
                message.set_message_type(1)
                message.set_arguments(response)
            
            elif message.get_method_name() == 'get_weather_temperature':
                response = sk_wt.get_weather_temperature(message.get_arguments())
                message.set_message_type(1)
                message.set_arguments(response)
            
            elif message.get_method_name() == 'get_weather_wind':
                response = sk_wt.get_weather_wind(message.get_arguments())
                message.set_message_type(1)
                message.set_arguments(response)

        return message
