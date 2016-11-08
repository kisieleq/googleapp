  $('#loginForm').validate({
      rules: {
    	  username: {
    		  required: true,
    		  minlength: 3
          },
          email: {
              required: true,
              minlength: 5
          },
          
          password: {
              required: true,
              minlength: 5
          }
      }
  });
  