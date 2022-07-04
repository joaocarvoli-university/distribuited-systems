import json
import server.RemoteObject as Remote
import skeletons.Weather as SkWeather
import skeletons.CurrencyConverter as SkCurrency


class Dispatcher:
    def invoke(self, message: json) -> json:
        """
        This method is responsible to select which skeleton of a specific service will be used
        :param message: The client message send from the client
        :return: the response message
        """
        sk_cc = SkCurrency.CurrencyConverter()
        sk_wt = SkWeather.Weather()
        remote_object = Remote.RemoteObject(message)
        response = {}

        if remote_object.get_service_name() == 'CurrencyConverter':
            del message['serviceName']
            if remote_object.get_method_name() == 'convert':
                del message['methodName']
                response = sk_cc.convert(message)
            elif remote_object.get_method_name() == 'currency_exists':
                del message['methodName']
                response = sk_cc.currency_exists(message)
            elif remote_object.get_method_name() == 'currencies_available':
                del message['methodName']
                response = sk_cc.currencies_available(message)

        elif remote_object.get_service_name() == 'WeatherAPI':
            del message['serviceName']
            if remote_object.get_method_name() == 'get_weather_clouds':
                del message['methodName']
                response = sk_wt.get_weather_clouds(message)
            elif remote_object.get_method_name() == 'get_weather_temperature':
                del message['methodName']
                response = sk_wt.get_weather_temperature(message)
            elif remote_object.get_method_name() == 'get_weather_wind':
                del message['methodName']
                response = sk_wt.get_weather_wind(message)

        return response
