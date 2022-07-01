import json
from services.currencyConverter import CurrencyConverter as ServiceConverter


class CurrencyConverter:
    def convert(self, message: json):
        params = get_params(message)
        converter = ServiceConverter.CurrencyConverter(base=params['fromCurrency'])
        result = converter.convert(currency_from=params['fromCurrency'],
                                   currency_to=params['toCurrency'],
                                   amount=params['amount'])

        message['result'] = result
        message['messageType'] = 1
        del message['arguments']
        return message

    def currencies_available(self, message: json):
        converter = ServiceConverter.CurrencyConverter(base='USD')
        result = converter.currencies_available()

        message['result'] = result
        message['messageType'] = 1
        del message['arguments']
        return message

    def currency_exists(self, message: json):
        params = get_params(message)
        converter = ServiceConverter.CurrencyConverter(base='USD')
        result = converter.currency_exists(currency=params['currency'])

        message['result'] = result
        message['messageType'] = 1
        del message['arguments']
        return message


def get_params(message: json):
    params = {}
    for i in message['arguments']:
        param, value = i.split(':')
        params[param] = value
    return params
