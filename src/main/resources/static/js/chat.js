$(function () {
  let getMessagesElement = function (message) {
    let item = $('<div class="message-item"></div>');
    let header = $('<div class="message-header"></div>');
    header.append($('<div class="dateTime">' + message.dateTime + '</div>'));
    header.append($('<div class="userName">' + message.username + '</div>'));
    let textElement = $('<div class="message-text">' + message + '</div>');
    textElement.text(message.text);
    item.append(header, textElement);
    return item;
  };

  let updateMessages = function () {
    $('.messages-list').html('');

    $.get('/message', {}, function (response) {
      if (response.length == 0) {
        return;
      }
      $('.messages-list').html('');
      for (let i in response) {
        let element = getMessagesElement(response[i]);
        $('.messages-list').append(element);
      }
    });
  };

  let initApplication = function () {
    $('.messages-and-users').css({display: 'flex'});
    $('.controls').css({display: 'flex'});
    $('.send-messages').on("click", function () {

      let message = $('.new-messages').val();
      $.post('/message', {message: message}, function (response) {
        if (response.result) {
          $('.new-messages').val('');
        } else {
          alert('Ошибка! (повторите попытку позже!)')
        }
      });
    });
    setInterval(updateMessages, 1000);

  };

  let registerUser = function (name) {
    $.post('/auth', {name: name}, function (response) {
      if (response.result) {
        initApplication();
      }
    });
  };

  $.get('/init', {}, function (response) {
    if (!response.result) {
      let name = prompt('Введите Ваше имя:');
      registerUser(name);
      return;
    } else {
      initApplication();
    }
  });
});