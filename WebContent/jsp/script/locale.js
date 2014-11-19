
function switchLang(locale,controller){
    data = $H( {
        locale : locale
    }).toQueryString();
    new Ajax.Request('switchLang.html?LOCALE='+locale+'&controller='+controller, {
        method : 'post',
        asynchronous : false,
        encoding : "UTF-8",
        postBody : data,
        onSuccess : function() {
            window.location.reload();
        },
        onFailure : function(transport) {
            alert("[Failure]" + transport.responseText);
        },
        onException : function(transport) {
            alert("[Exception]" + transport.responseText);
        }
    });
}