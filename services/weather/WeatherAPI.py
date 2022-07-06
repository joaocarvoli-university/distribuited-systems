import requests
import json
import os
from dotenv import load_dotenv

load_dotenv()
env = {
    'api_key':os.getenv('API_KEY_WEATHER')
}

api_key = env['api_key']

# classe WeatherCity
class WeatherCity:
    """  Class that shows weather data from a particular city using OpenWeatherMap's API
  """

    def __init__(self, city):
        """Class constructor

    :param city: the city which the weather data will be provided
    :param api_key: the key provided by an account in https://home.openweathermap.org/api_keys
    """
        self.city = city
        self.api_key = api_key

    def get_weather_complete_data(self):
        """
    Returns: 
    str -- Complete JSON data from OpenWeatherMap;(See complete documentation at: https://openweathermap.org/current#parameter)
    """

        return requests.get(
            f"https://api.openweathermap.org/data/2.5/weather?units=metric&q={self.city}&appid={self.api_key}").text

    # metodo weather cloud
    def get_weather_clouds(self):
        """City's weather state
    Returns: 
        str -- Return JSON containing cloud's state data (Use key 'clouds' to get the data)
    """

        weather_data = self.get_weather_complete_data()
        # convert to object
        weather_data_object = json.loads(weather_data)
        clouds = weather_data_object["weather"][0]["description"]
        dictionary = {'clouds': clouds}
        return json.dumps(dictionary)

    # metodo weather temperature
    def get_weather_temperature(self):
        """City's temperature in °C

    Returns:
        str -- Return JSON containing temperature data in °C (Use key 'temperature' to get the data)
    """
        weather_data = self.get_weather_complete_data()
        # convert to object
        weather_data_object = json.loads(weather_data)
        temperature = weather_data_object["main"]["temp"]
        dictionary = {'temperature': temperature}
        return json.dumps(dictionary)

    # metodo weather wind
    def get_weather_wind(self):
        """City's wind speed in m/s

    Returns:
        str -- Return JSON containing wind speed data in m/s (Use key 'wind' to get the data)
    """
        weather_data = self.get_weather_complete_data()
        # convert to object
        weather_data_object = json.loads(weather_data)
        wind = weather_data_object["wind"]["speed"]
        dictionary = {'wind': wind}
        return json.dumps(dictionary)
