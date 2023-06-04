(function () {
  'use strict';

  // Custom scripts
  document.addEventListener("DOMContentLoaded", function() {

    // Live Awesomplete Search 
  	var inputHero = document.getElementById("search-hero");
  	var inputNavbar = document.getElementById("search-navbar");

    var list = [
        ];

    if (inputHero) {
      inputHero.addEventListener("awesomplete-selectcomplete", function(e) {
        window.location.href = e.text.value;
      }, false);
      
      new Awesomplete(inputHero, {
        autoFirst: true,
        list: list,
        replace: function(suggestion) {
          this.input.value = suggestion.label;
        }
      });
    }

    if (inputNavbar) {
      inputNavbar.addEventListener("awesomplete-selectcomplete", function(e) {
        window.location.href = e.text.value;
      }, false);
      
      new Awesomplete(inputNavbar, {
        autoFirst: true,
        list: list,
        replace: function(suggestion) {
          this.input.value = suggestion.label;
        }
      });
    }

  });

}());
