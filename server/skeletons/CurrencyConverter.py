from services.currencyConverter import CurrencyConverter as ServiceConverter


class CurrencyConverter:
    def convert(self, args: list):
        params = get_params(args)
        converter = ServiceConverter.CurrencyConverter(base=params['fromCurrency'])
        result = converter.convert(
            currency_from=params['fromCurrency'],
            currency_to=params['toCurrency'],
            amount=params['amount']
        )
        result = str(result)
        result = result.replace('{', '')
        result = result.replace('}', '')
        result = result.replace("'", '')
        print(result)
        response = list()
        response.append(result)
        return response

    def currency_exists(self, args: list):
        params = get_params(args)
        converter = ServiceConverter.CurrencyConverter(base='USD')
        result = converter.currency_exists(currency=params['currency'])
        result = str(result)
        result = result.replace('{', '')
        result = result.replace('}', '')
        result = result.replace("'", '')
        response = list()
        response.append(result)
        return response

    def currencies_available(self):
        converter = ServiceConverter.CurrencyConverter(base='USD')
        result = converter.currencies_available()
        response = list()
        for i, currency in enumerate(result):
            string = 'currency:' + currency
            response.append(string)
        print(response)
        return response


def get_params(args: list):
    params = {}
    for i in args:
        param, value = i.split(':')
        params[param] = value
    return params
