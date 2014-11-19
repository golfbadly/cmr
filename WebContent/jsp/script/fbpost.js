//API init code is omitted
FB.login(function() {
    FB.api('/me/accounts', 'get', {}, function(response) {
        console.log(response);
    });
}, {perms:'publish_stream,offline_access,manage_pages'});