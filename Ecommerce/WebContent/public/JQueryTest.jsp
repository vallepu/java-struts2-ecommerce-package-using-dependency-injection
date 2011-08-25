<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <html lang="en">
 <head>
   <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
   
  <script type='text/javascript' src='jquery-1.4.3.js'></script>
  <script type='text/javascript' src='jquery.gallery.js'></script>    


  <script type="text/javascript">
     $(document).ready(function(){ 
       $("#gallery-wrapper").gallery({
       background: '#ffc',
       foreground: '#000'                          
       });
     });
     
   </script>

 </head>
 <body>
  
  
  <div id="gallery-wrapper">
  <h1 class="gallery-title">Opera House</h1>
 
  <div id="gallery-image" style="height:300px; width: 400px;"> <img src="http://eisabainyo.net/demo/images/image1.jpg" width="400" height="300" title="Opera House" alt="Opera House" /> </div>
  <div class="gallery-more">
    <ul>
      <li><a href="http://eisabainyo.net/demo/images/image1.jpg" title="Opera House" class="t1 active"><img src="http://eisabainyo.net/demo/images/thumb1.jpg" width="60" height="50" alt="Opera House" /><span>Opera House</span></a></li>
      <li><a href="http://eisabainyo.net/demo/images/image2.jpg" title="Harbour Bridge" class="t2"><img src="http://eisabainyo.net/demo/images/thumb2.jpg" width="60" height="50" alt="Harbour Bridge" /><span>Harbour Bridge</span></a></li>
      <li><a href="http://eisabainyo.net/demo/images/image3.jpg" title="Sydney from the above" class="t3"><img src="http://eisabainyo.net/demo/images/thumb3.jpg" width="60" height="50" alt="Sydney from the above" /><span>Sydney from the above</span></a></li>
      <li><a href="http://eisabainyo.net/demo/images/image4.jpg" title="Sydney Buildings" class="t4"><img src="http://eisabainyo.net/demo/images/thumb4.jpg" width="60" height="50" alt="Sydney Buildings" /><span>Sydney Buildings</span></a></li>
      <li><a href="http://eisabainyo.net/demo/images/image5.jpg" title="The Iconic Opera House" class="t5"><img src="http://eisabainyo.net/demo/images/thumb5.jpg" width="60" height="50" alt="The Iconic Opera House" /><span>The Iconic Opera House</span></a></li>
    </ul>
  </div>
</div>
  
  
 </body>
 </html>

