// $(document).ready(function(){
//     $(".add_to_bag_btn").click(function(e){
//         e.preventDefault(); // Prevent the default form submission
        
//         var form = $(this).closest("form"); 
//         var itemId = form.find("input[name='itemId']").val(); 
        
      
//         $.ajax({
//             url: "/cart/add", 
//             method: "POST", 
//             data: { itemId: itemId },
//             success: function(response){
                
//                 console.log("item" + itemId);
//                 $(".popup").show();
//             },
//             error: function(xhr, status, error){
               
//                 console.error(xhr.responseText);
//             }
//         });
//     });
  
//     $(".close-popup").click(function(){
     
//         $(".popup").hide();
//     });
//   });
  
//   $(document).ready(function(){
 
//     $(".decrement").click(function(){
//         var inputField = $(this).closest(".quantity_range").find(".quantityvalue");
//         var itemId = inputField.data("itemid");
//         var currentValue = parseInt(inputField.val());
//         if (currentValue > 1) { 
//             inputField.val(currentValue - 1);
//             updateQuantityInDatabase(itemId, currentValue - 1);
//         }
//     });

    
//     $(".increment").click(function(){
//         var inputField = $(this).closest(".quantity_range").find(".quantityvalue");
//         var itemId = inputField.data("itemid");
//         var currentValue = parseInt(inputField.val());
//         inputField.val(currentValue + 1);
//         updateQuantityInDatabase(itemId, currentValue + 1);
//     });

   
//     function updateQuantityInDatabase(itemId, quantity) {
//         $.ajax({
//             url: "/cart/updateQuantity",
//             method: "POST",
//             data: { itemId: itemId, quantity: quantity },
//             success: function(response) {
              
//                 console.log("Quantity updated successfully.");
//             },
//             error: function(xhr, status, error) {
               
//                 console.error("Error updating quantity:", xhr.responseText);
//             }
//         });
//     }
// });
// $(document).ready(function() {
//     $('.button-remove').click(function() {
//         var itemId = $(this).closest(".Qty_div").find(".quantityvalue").data("itemid");
//         console.log("Item ID:", itemId); 
//         var $lineItem = $(this).closest('tr');
//         $.ajax({
//             url: '/cart/remove',
//             method: 'POST',
//             data: { itemId: itemId },
//             success: function(response) {
//                 $lineItem.remove();
                
//                 console.log('Item removed successfully!');
//             },
//             error: function(xhr) {
//                 console.log('Error removing item');
//             }
//         });
//     });
// });

$(document).ready(function(){
  $(".add_to_bag_btn").click(function(e){
      e.preventDefault(); // Prevent the default form submission
      
      var form = $(this).closest("form"); 
      var itemId = form.find("input[name='itemId']").val(); 
      
    
      $.ajax({
          url: "/cart/add", 
          method: "POST", 
          data: { itemId: itemId },
          success: function(response){
              
              console.log("item" + itemId);
              $(".popup").show();
          },
          error: function(xhr, status, error){
             
              console.error(xhr.responseText);
          }
      });
  });

  $(".close-popup").click(function(){
   
      $(".popup").hide();
  });
});

$(document).ready(function(){

  $(".decrement").click(function(){
      var inputField = $(this).closest(".quantity_range").find(".quantityvalue");
      var itemId = inputField.data("itemid");
      var currentValue = parseInt(inputField.val());
      if (currentValue > 1) { 
          inputField.val(currentValue - 1);
          updateQuantityInDatabase(itemId, currentValue - 1);
      }
  });

  
  $(".increment").click(function(){
      var inputField = $(this).closest(".quantity_range").find(".quantityvalue");
      var itemId = inputField.data("itemid");
      var currentValue = parseInt(inputField.val());
      inputField.val(currentValue + 1);
      updateQuantityInDatabase(itemId, currentValue + 1);
  });

 
  function updateQuantityInDatabase(itemId, quantity) {
      $.ajax({
          url: "/cart/updateQuantity",
          method: "POST",
          data: { itemId: itemId, quantity: quantity },
          success: function(response) {
            
              console.log("Quantity updated successfully.");
          },
          error: function(xhr, status, error) {
             
              console.error("Error updating quantity:", xhr.responseText);
          }
      });
  }
});
$(document).ready(function() {
  $('.button-remove').click(function() {
      var itemId = $(this).closest(".Qty_div").find(".quantityvalue").data("itemid");
      console.log("Item ID:", itemId); 
      var $lineItem = $(this).closest('tr');
      $.ajax({
          url: '/cart/remove',
          method: 'POST',
          data: { itemId: itemId },
          success: function(response) {
              $lineItem.remove();
              
              console.log('Item removed successfully!');
          },
          error: function(xhr) {
              console.log('Error removing item');
          }
      });
  });
});
