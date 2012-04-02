Hypothetical application is working with remote system through Web Services.
The connection to the remote system is slow and application need to cache remote objects. 
It takes 0.5 sec to get object from remote system and object can be up to 1 Mb. 
Application can use unlimited amount of remote objects.

Object on remote system can be changed without actions from our side. Objects are identified with ID and last update time.

Goal 
1. Create cache mechanism for generic objects
2. Preferably to have two levels of cache and synchronization mechanism (update or remove out-dated objects).
3. Preferably to have cache size configurable.
4. Create testing system.
5. Web Services mechanism is not obligatory for implementation but nice to have.
