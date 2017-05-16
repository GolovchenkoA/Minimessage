/* Scripts for buttons subscribe and unsubscribe   */


function subscribeajax() {
    $(".subscribe_button").submit(function (event) {

        event.preventDefault();

        var $form = $(this), url = $form.attr('action');
        //var posting = $.post(url, {})
        var account_login = $(this).closest('tr').find('.login_in_table').text();
        var posting = $.post(url, {publisher_account: account_login})

        posting.done(function (data) {
            alert('Subscription done');
        })
    })
}



// table row id=login_in_table
function subscribe(){

        $('.subscribe').click(function(){
            var account_login = $(this).closest("tr").find(".login_in_table").text();
            alter('subscribe to ' + account_login)
        })
}
/*
function subscribe(){
    $(document).ready(function(){
        $('#subscribe').click(function(){
            var account_login = $(this).parent().siblings('.login_in_table').text();
            alter('subscribe to ' + account_login)
        })
    })
}
*/

function unsubscribe(){
    $(document).ready(function(){
        $('.unsubscribe').click(function(){
            var account_login = $(this).parent().siblings('.login_in_table').text();
            alter('unsubscribe to ' + account_login)
        })
    })
}