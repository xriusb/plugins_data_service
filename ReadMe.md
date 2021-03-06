# Getting Started

This is service application that listens requests through the port 80 from different client plugins, and returns the next information:
- A view identifier (h)
- A ping time used by the device (q)
- An application generated unique view code with only numbers and letters (c)

This will be returned in a XML format like this:
```
<?xml version='1.0' encoding='UTF-8'?> 
<q>
   <h>clusterA.com</h> 
   <pt>5</pt> 
   <c>7xnj85f06yqswc5x</c>
</q>
```


To configure the service port it has to be modified the application properties file. 
The application loads in it start up a couple of accounts that you could find in the location of resource/data/accounts.json
After, new accounts can be load it from a another service end point. 

### API End Points

- **getPluginData** used to retrieve the plugin data from the parameters passed. 
    + method = GET 
    + mapping = /getData  
    + query parameters = 
        + accountCode 
        + targetDevice 
        + pluginVersion
    + example = localhost:80/getData?accountCode=clienteA&targetDevice=XBox&pluginVersion=3.3.1
    + returns => XML format containing with host, ping time and view code
    + If there's no account match, returns an empty response. 
    + If there's no device match, returns an empty device. 
    
- **createAccounts** used to create accounts to the application, deleting any existing. 
    + method = POST 
    + mapping = /accounts  
    + request body = JSON format (sample can be found in resource/data/accounts.json)
    + returns => JSON format with the new stored accounts