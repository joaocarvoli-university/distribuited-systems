
class GandPy:

    
    def __init__(self, cache_IDs_limit = 500, errorRaise = True):
        """
        :param cache_IDs_limit: The amount of messages that will be saved to avoid duplicate before reset the cache
        :param errorRaise: Raises on the console a Error every time a duplicated message appears
        """
        self.data_ids_received = []
        self.errorRaise = errorRaise
        self.cache_IDs_limit = cache_IDs_limit


    def message_ID_shall_pass(self, message_id):
        """
        :param message_id: The ID from the message received
        """
        if message_id not in self.data_ids_received:
            self.__add_ID_to_database__(message_id)

            return True 

        else:
            if(self.errorRaise):
                print("Error: Duplicated  Message ID '" + str(message_id) + "'-- Shall not Pass)")

            return False


    def __add_ID_to_database__(self, message_id):
        if len(self.data_ids_received) >= self.cache_IDs_limit:
            self.data_ids_received = [] 

        self.data_ids_received.append(message_id)



def main():

    barrier = GandPy(cache_IDs_limit=0)
    message_id = 3219


    #First Message
    if barrier.message_ID_shall_pass(message_id):
        print('Passou')
    else:
        print('Não Passou')

    #Duplicated Message
    message_id = 3219
    if barrier.message_ID_shall_pass(message_id):
        print('Passou')
    else:
        print('Não Passou')



if __name__ == '__main__':
    main()

