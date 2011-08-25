(function($) {
	$.fn.gallery = function(options) { 
		var defaults = {
			background: '#000',
			foreground: '#fff'
		};

		var opts = $.extend(defaults, options);
		
		return this.each(function(){
			
			$this = $(this);
			
			var o = $.meta ? $.extend({}, opts, $this.data()) : opts;
			// update element styles
			$this.css({
				backgroundColor: o.background,
				color: o.foreground
			});

			
			var pos = 1; // array index of currently displayed image
			var total = $('.gallery-more ul').children().length; // total number of images
			
			$('.gallery-pagination .total-pos').html(total);		
			
			get_prev_next(pos);
			
			function get_current_image(image_url, image_alt) {
				
				$('.gallery-title').html(image_alt); // assign Image Heading title
				$('#gallery-image img').attr("alt", image_alt);  // assign Image alt
				$('#gallery-image img').attr("title", image_alt);  // assign Image title
				$('#gallery-image img').attr("src", image_url).load(function(){
					$('#gallery-image img').hide().fadeIn("fast");                      
				});

			};
		
			function get_prev_next(thumb) {
	
				//update the current position before doing anything
				$('.gallery-pagination .current-pos').html(thumb);

				if (thumb <= 1) {
					var prev_image_thumb = total;
				} else {
					var prev_image_thumb = parseInt(thumb)-1;
				}
				
				if (thumb >= total) {
					var next_image_thumb = 1;
				} else {
					var next_image_thumb = parseInt(thumb)+1;	
				}
				
				var prev_image_url = $('.gallery-more ul li a.t'+ prev_image_thumb).attr("href");
				var prev_image_alt = $('.gallery-more ul li a.t'+ prev_image_thumb).attr("title");
										
				var next_image_url = $('.gallery-more ul li a.t'+ next_image_thumb).attr("href");
				var next_image_alt = $('.gallery-more ul li a.t'+ next_image_thumb).attr("title");
						
				$('.gallery-pagination a.prev').attr("href", prev_image_url);
				$('.gallery-pagination a.prev').attr("title", prev_image_alt);
				$('.gallery-pagination a.prev').attr("rel", prev_image_thumb);
				
				$('.gallery-pagination a.next').attr("href", next_image_url);
				$('.gallery-pagination a.next').attr("title", next_image_alt);
				$('.gallery-pagination a.next').attr("rel", next_image_thumb);
			};
			
			$('.gallery-more ul li a', this).click(function() {
		  
				var image_url = $(this).attr("href");
				var image_alt = $(this).children("img").attr("alt");
				
				$('.gallery-more ul li a').removeClass('active');
				var thumb_class = $(this).attr("class");
				var thumb = thumb_class.slice(1, thumb_class.length);
				$(this).addClass('active');
				
				get_current_image(image_url, image_alt);

				get_prev_next(thumb);
				
				return false;
			});

			$('.gallery-pagination a', this).click(function() {
				
				// get image details
				var image_url = $(this).attr("href");
				var image_alt = $(this).attr("title");
				var thumb = $(this).attr("rel");
			
				// select an appropraite thumbnail
				$('.gallery-more ul li a').removeClass('active');
				$('.gallery-more ul li a.t'+ thumb).addClass('active');
				
				get_current_image(image_url, image_alt);

				get_prev_next(thumb);
			
				return false;
			});
		
		}); // this.each
		
	}; // fn.gallery
})(jQuery);  // function($)

$(document).ready(function() {
	$("#gallery-wrapper").gallery({
			background: '#ffc',
			foreground: '#000'						  
	 });

});