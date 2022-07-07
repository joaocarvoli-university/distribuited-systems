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

        return result

    def currencies_available(self):
        converter = ServiceConverter.CurrencyConverter(base='USD')
        result = converter.currencies_available()
        return result

    def currency_exists(self, args: list):
        params = get_params(args)
        converter = ServiceConverter.CurrencyConverter(base='USD')
        result = converter.currency_exists(currency=params['currency'])

        return result


def get_params(args: list):
    params = {}
    for i in args:
        param, value = i.split(':')
        params[param] = value
    return params
