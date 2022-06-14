import ExchangeRate as Er


class CurrencyConverter:
    def __init__(self):
        self.converter = Er.ExchangeRate('https://api.exchangerate.host/latest')

    def convert(self, currency_from: str, currency_to: str, amount: float) -> float:
        """
        This function convert the currency from some to others
        :param currency_from: A string that represents your actual currency, the currency that you have
        :param currency_to:  A string that represents the currency that you want to convert to
        :param amount: The amount of money from param 'currency_from'
        :return: The value converted
        """
        from_currency = self.converter.get_rates()[currency_from]
        to_currency = self.converter.get_rates()[currency_to]
        conversion = (from_currency / to_currency) * amount
        return conversion

    def currency_exists(self, currency: str) -> bool:
        """
        This function checks if a specific currency exists
        :param currency: The acronyms of currency
        :return:A boolean that represents if this currency exists or not on used api
        """
        if currency in self.converter.get_rates():
            return True
        return False

    def currencies_available(self):
        """
        :return: A list of all currencies available to use
        """
        return list(self.converter.get_rates().keys())
