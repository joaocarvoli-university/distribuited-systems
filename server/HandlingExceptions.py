class HandlingExceptions:

    def __init__(self, cache_ids_limit=500, logEnabled=True):
        """
        :param cache_ids_limit: The amount of messages that will be saved to avoid duplicate before reset the cache
        :param errorRaise: Raises on the console a Error every time a duplicated message appears
        """
        self.data_ids_received = {}
        self.logEnabled = logEnabled
        self.cache_ids_limit = cache_ids_limit

    def get_cache_from_request_id(self, message_id):
        if message_id not in self.data_ids_received:
            if self.logEnabled:
                print('Response from id: ' + str(message_id) + ' is not in cache. Returning NULL as response')
            return None

        cached_response = self.data_ids_received[message_id]

        if self.logEnabled:
            print('Retrieved cached response from id: ' + str(message_id))
            print('Response: ' + cached_response)

        return cached_response

    def add_cache_to_request_id(self, message_id, response):
        self.__verify_limit_cache__()

        if response:
            self.data_ids_received[message_id] = response
            if self.logEnabled:
                print('Cached response from id: ' + str(message_id))

    def __verify_limit_cache__(self):
        if len(self.data_ids_received) >= self.cache_ids_limit:
            if self.logEnabled:
                print('Warning: Cache overflow! Cleaning Cache.')
            self.data_ids_received = {}
