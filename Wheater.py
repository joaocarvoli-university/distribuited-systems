import requests
import json 

#classe WeatherCity
class WeatherCity:
  def __init__(self, city, api_key):
    self.city = city
    self.api_key = api_key
    
  
  def get_weather_complete_data(self):
     return requests.get(
    f"https://api.openweathermap.org/data/2.5/weather?units=metric&q={self.city}&appid={self.api_key}").text

  
  #metodo weather cloud
  def get_weather_clouds(self):
     weather_data = self.get_weather_complete_data()
     #convert to object
     weather_data_object = json.loads(weather_data)
     clouds = weather_data_object["weather"][0]["description"]
     dictionary = {'clouds':clouds}
     return json.dumps(dictionary)

  #metodo weather temperature
  def get_weather_temperature(self):
      weather_data = self.get_weather_complete_data()
      #convert to object
      weather_data_object = json.loads(weather_data)
      temperature = weather_data_object["main"]["temp"]
      dictionary = {'temperature':temperature}
      return json.dumps(dictionary)
  
  #metodo weather wind
  def get_weather_wind(self):
      weather_data = self.get_weather_complete_data()
      #convert to object
      weather_data_object = json.loads(weather_data)
      wind = weather_data_object["wind"]["speed"]
      dictionary = {'weather':wind}
      return json.dumps(dictionary)


api_key = '207b8be31a9062d5eff256f1acb51668'
user_input = input("Cidade: ")

#tempoFortaleza = WeatherCity("Fortaleza",api_key)
tempoUser = WeatherCity(user_input,api_key)

print('Temperatura:',tempoUser.get_weather_temperature(),'°C')
print('Nuvens:',tempoUser.get_weather_clouds())
print('Velocidade do vento:',tempoUser.get_weather_wind(),'m/s')

