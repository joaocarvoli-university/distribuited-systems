import json
#import services.currencyConverter.CurrencyConverter as service


class CurrencyConverter:
    def convert(message: json):
        for i in message['arguments']:
            param, value = i.split(':')
            print(param, value)
        #service.CurrencyConverter.convert(message)


    def currency_exists(message: json):
        print('oi')

    def currencies_available(message: json):
        print('oie')
