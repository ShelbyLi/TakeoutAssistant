<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>外卖小助手 - rider-basic info</title>
  <meta charset="utf-8" />
  <link rel="apple-touch-icon" sizes="76x76" href="assets/img/apple-icon.png">
  <link rel="icon" type="image/png" href="assets/img/favicon.png">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <meta content='width=device-width, initial-scale=1.0, shrink-to-fit=no' name='viewport' />
  <!--     Fonts and icons     -->
  <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Roboto+Slab:400,700|Material+Icons" />
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">
  <!-- CSS Files -->
  <link href="assets/css/material-dashboard.css?v=2.1.2" rel="stylesheet" />
  <!-- CSS Just for demo purpose, don't include it in your project -->
  <link href="assets/demo/demo.css" rel="stylesheet" />
</head>
<body>
  <div class="wrapper ">
    <div class="sidebar" data-color="purple" data-background-color="white">
      <div class="logo">
        <a href="#" class="simple-text logo-mini">外卖小助手</a>
        <a href="#" class="simple-text logo-normal">你好! 骑手 ${cur_rider.rider_name }</a>
      </div>
	  
      <div class="sidebar-wrapper">
        <ul class="nav">
			
			
          <li class="nav-item ">
            <a class="nav-link" href="RiderToReceive">
              <i class="material-icons">content_paste</i>
              <p>去接单</p>
            </a>
          </li>
          <!-- your sidebar here -->
		  <li class="nav-item ">
            <a class="nav-link" href="RiderToDeliver">
              <i class="material-icons">dashboard</i>
              <p>去送单</p>
            </a>
          </li>
		  
		  <li class="nav-item ">
		    <a class="nav-link" href="RiderDeliveredOrder">
		      <i class="material-icons">library_books</i>
		      <p>查看已送订单</p>
		    </a>
		  </li>
		  
		  <li class="nav-item active  ">
		    <a class="nav-link" href="RiderBasicInfo">
		      <i class="material-icons">person</i>
		      <p>我</p>
		    </a>
		  </li>
		  
		  
        </ul>
      </div>
    </div>
	
	
	
    <div class="main-panel">
      <!-- Navbar -->
      <nav class="navbar navbar-expand-lg navbar-transparent navbar-absolute fixed-top ">
        <div class="container-fluid">
          <div class="navbar-wrapper">
            <!-- <a class="navbar-brand" href="javascript:;">Dashboard</a> -->
          </div>
          <button class="navbar-toggler" type="button" data-toggle="collapse" aria-controls="navigation-index" aria-expanded="false" aria-label="Toggle navigation">
            <span class="sr-only">Toggle navigation</span>
            <span class="navbar-toggler-icon icon-bar"></span>
            <span class="navbar-toggler-icon icon-bar"></span>
            <span class="navbar-toggler-icon icon-bar"></span>
          </button>
          <div class="collapse navbar-collapse justify-content-end">
            <!-- <form class="navbar-form" method="post">
              <div class="input-group no-border">
                <input type="text" value="" class="form-control" placeholder="Search..." name="keyWord">
                <button type="submit" class="btn btn-white btn-round btn-just-icon">
                  <i class="material-icons">search</i>
                  <div class="ripple-container"></div>
                </button>
              </div>
            </form> -->
            <ul class="navbar-nav">


              <li class="nav-item dropdown">
                <a class="nav-link" href="javascript:;" id="navbarDropdownProfile" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                  <i class="material-icons">person</i>
                  <p class="d-lg-none d-md-block">
                    Account
                  </p>
                </a>
                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownProfile">
                  <a class="dropdown-item" href="rider_basicinfo.jsp">我</a>
                  <div class="dropdown-divider"></div>
                  <a class="dropdown-item" href="rider_login.jsp">退出</a>
                </div>
              </li>
            </ul>
          </div>
        </div>
      </nav>
      <!-- End Navbar -->
      <div class="content">
        <div class="container-fluid">
          <!-- your content here -->
		  <div class="row">
		    <!-- <div class="col-lg-6 col-md-12"> -->
		      <div class="card">
		          <div class="card-header card-header-primary">
		            <h4 class="card-title">基本信息</h4>
		            <p class="card-category">完善你的信息</p>
		          </div>
		          <div class="card-body">
		            <form action="RiderEditBasicInfo" method="post">
		              <div class="row">
		                <div class="col-md-4">
		                  <div class="form-group">
		                    <label class="bmd-label-floating">骑手ID</label>
		                    <input type="text" class="form-control" disabled value="${cur_rider.rider_id }">
		                  </div>
		                </div>
		                <div class="col-md-4">
		                  <div class="form-group">
		                    <label class="bmd-label-floating">骑手名</label>
		                    <input type="text" class="form-control" value="${cur_rider.rider_name }" name="rider_name" required="required">
		                  </div>
		                </div>
		                <div class="col-md-4">
		                  <div class="form-group">
		                    <label class="bmd-label-floating">身份</label>
		                    <%-- <input type="text" class="form-control" disabled="disabled" value="${cur_rider.rider_identity }"> --%>
		                    <input type="text" class="form-control" disabled="disabled" value="新人">
		                  </div>
		                </div>
		              </div>
		              
		              <div class="row">
		                <div class="col-md-4">
		                  <div class="form-group">
		                    <label class="bmd-label-floating">入职时间</label>
		                    <input type="text" class="form-control" disabled="disabled" name="rider_entry_date" value="${cur_rider.rider_entry_date }">
		                  </div>
		                </div>
		                <div class="col-md-4">
		                  <div class="form-group">
		                    <label class="bmd-label-floating">已送订单数</label>
		                    <input type="text" class="form-control" disabled="disabled" name="deliver_cnt" value="${cur_rider.deliver_cnt }">
		                  </div>
		                </div>
		                <div class="col-md-4">
		                  <div class="form-group">
		                    <label class="bmd-label-floating">总收入</label>
		                    <input type="text" class="form-control" disabled="disabled" name="rider_total_income" value="${cur_rider.rider_total_income }">
		                  </div>
		                </div>
		              </div>
		              <!-- <div class="row">
		                <div class="col-md-12">
		                  <div class="form-group">
		                    <label>简介</label>
		                    <div class="form-group">
		                      <label class="bmd-label-floating">介绍一下自己吧 </label>
		                      <textarea class="form-control" rows="5"></textarea>
		                    </div>
		                  </div>
		                </div>
		              </div> -->
		              
					  <button type="submit" class="btn btn-primary pull-right">上传修改</button>
		              
		              <div class="clearfix"></div>
		            </form>
		          </div>
		      </div>
		      
			  
			  
			  <!-- </div> -->
		    </div>
		  
		  <div class="row">
		    <!-- <div class="col-lg-6 col-md-12"> -->
		      <div class="card">
		          <div class="card-header card-header-primary">
		            <h4 class="card-title">修改密码</h4>
		            <p class="card-category">提高保密性</p>
		          </div>
		          <div class="card-body">
		            <form action="RiderChangepwd" method="post">		              
		              <div class="row">
		                <div class="col-md-12">
		                  <div class="form-group">
		                    <label class="bmd-label-floating">旧密码</label>
		                    <input type="password" class="form-control" name="old_pwd" required="required">
		                  </div>
		                </div>
		                </div>
		              <div class="row">
		                <div class="col-md-6">
		                  <div class="form-group">
		                    <label class="bmd-label-floating">新密码</label>
		                    <input type="password" class="form-control" name="new_pwd" required="required">
		                  </div>
		                </div>
		                <div class="col-md-6">
		                  <div class="form-group">
		                    <label class="bmd-label-floating">确认密码</label>
		                    <input type="password" class="form-control" name="new_pwd_check" required="required">
		                  </div>
		                </div>
		              </div>
		              <button type="submit" class="btn btn-primary pull-right" name="changpwd">修改密码</button>
		              <div class="clearfix"></div>
		            </form>
		          </div>
		    </div>
		  </div>
		  
		  <div class="row">
		    <!-- <div class="col-lg-6 col-md-12"> -->
		      <div class="card">
		          <div class="card-header card-header-primary">
		            <h4 class="card-title">注销</h4>
		            <p class="card-category">你确定要离开外卖小助手吗? 🙁</p>
		          </div>
		          <div class="card-body">
		            <form action="ShopLogout" method="post">		              
		              <div class="row">
		                
		              </div>
		              <button type="submit" class="btn btn-primary pull-right" name="logout">注销</button>
		              <div class="clearfix"></div>
		            </form>
		          </div>
		    </div>
		  </div>
		  
        </div>
      <footer class="footer">
			<div class="container-fluid">
				<nav class="float-left">
					<ul>
						<li>
							<a href="#">
								About Us
							</a>
						</li>
						<li>
							<a href="#">
								Github
							</a>
						</li>
						<li>
							<a href="#">
								Licenses
							</a>
						</li>
					</ul>
				</nav>
				<div class="copyright float-right">
					&copy;
					<script>
						document.write(new Date().getFullYear())
					</script>, made with <i class="material-icons">favorite</i> by
					<a href="#" target="_blank">Shelby Li</a> CS1801 ZUCC
				</div>
			</div>
		</footer>

	  </div>
	  </div>
  </div>
</body>

  <script src="assets/js/core/jquery.min.js"></script>
  <script src="assets/js/core/popper.min.js"></script>
  <script src="assets/js/core/bootstrap-material-design.min.js"></script>
  <script src="assets/js/plugins/perfect-scrollbar.jquery.min.js"></script>
  <!-- Plugin for the momentJs  -->
  <script src="assets/js/plugins/moment.min.js"></script>
  <!--  Plugin for Sweet Alert -->
  <script src="assets/js/plugins/sweetalert2.js"></script>
  <!-- Forms Validations Plugin -->
  <script src="assets/js/plugins/jquery.validate.min.js"></script>
  <!-- Plugin for the Wizard, full documentation here: https://github.com/VinceG/twitter-bootstrap-wizard -->
  <script src="assets/js/plugins/jquery.bootstrap-wizard.js"></script>
  <!--	Plugin for Select, full documentation here: http://silviomoreto.github.io/bootstrap-select -->
  <script src="assets/js/plugins/bootstrap-selectpicker.js"></script>
  <!--  Plugin for the DateTimePicker, full documentation here: https://eonasdan.github.io/bootstrap-datetimepicker/ -->
  <script src="assets/js/plugins/bootstrap-datetimepicker.min.js"></script>
  <!--  DataTables.net Plugin, full documentation here: https://datatables.net/  -->
  <script src="assets/js/plugins/jquery.dataTables.min.js"></script>
  <!--	Plugin for Tags, full documentation here: https://github.com/bootstrap-tagsinput/bootstrap-tagsinputs  -->
  <script src="assets/js/plugins/bootstrap-tagsinput.js"></script>
  <!-- Plugin for Fileupload, full documentation here: http://www.jasny.net/bootstrap/javascript/#fileinput -->
  <script src="assets/js/plugins/jasny-bootstrap.min.js"></script>
  <!--  Full Calendar Plugin, full documentation here: https://github.com/fullcalendar/fullcalendar    -->
  <script src="assets/js/plugins/fullcalendar.min.js"></script>
  <!-- Vector Map plugin, full documentation here: http://jvectormap.com/documentation/ -->
  <script src="assets/js/plugins/jquery-jvectormap.js"></script>
  <!--  Plugin for the Sliders, full documentation here: http://refreshless.com/nouislider/ -->
  <script src="assets/js/plugins/nouislider.min.js"></script>
  <!-- Include a polyfill for ES6 Promises (optional) for IE11, UC Browser and Android browser support SweetAlert -->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/core-js/2.4.1/core.js"></script>
  <!-- Library for adding dinamically elements -->
  <script src="assets/js/plugins/arrive.min.js"></script>
  <!--  Google Maps Plugin    -->
  <!-- <script src="https://maps.googleapis.com/maps/api/js?key=YOUR_KEY_HERE"></script> -->
  <!-- Chartist JS -->
  <script src="assets/js/plugins/chartist.min.js"></script>
  <!--  Notifications Plugin    -->
  <script src="assets/js/plugins/bootstrap-notify.js"></script>
  <!-- Control Center for Material Dashboard: parallax effects, scripts for the example pages etc -->
  <script src="assets/js/material-dashboard.js?v=2.1.2" type="text/javascript"></script>
  <!-- Material Dashboard DEMO methods, don't include it in your project! -->
  <script src="assets/demo/demo.js"></script>
  <script>
    $(document).ready(function() {
      $().ready(function() {
        $sidebar = $('.sidebar');

        $sidebar_img_container = $sidebar.find('.sidebar-background');

        $full_page = $('.full-page');

        $sidebar_responsive = $('body > .navbar-collapse');

        window_width = $(window).width();

        fixed_plugin_open = $('.sidebar .sidebar-wrapper .nav li.active a p').html();

        if (window_width > 767 && fixed_plugin_open == 'Dashboard') {
          if ($('.fixed-plugin .dropdown').hasClass('show-dropdown')) {
            $('.fixed-plugin .dropdown').addClass('open');
          }

        }

        $('.fixed-plugin a').click(function(event) {
          // Alex if we click on switch, stop propagation of the event, so the dropdown will not be hide, otherwise we set the  section active
          if ($(this).hasClass('switch-trigger')) {
            if (event.stopPropagation) {
              event.stopPropagation();
            } else if (window.event) {
              window.event.cancelBubble = true;
            }
          }
        });

        $('.fixed-plugin .active-color span').click(function() {
          $full_page_background = $('.full-page-background');

          $(this).siblings().removeClass('active');
          $(this).addClass('active');

          var new_color = $(this).data('color');

          if ($sidebar.length != 0) {
            $sidebar.attr('data-color', new_color);
          }

          if ($full_page.length != 0) {
            $full_page.attr('filter-color', new_color);
          }

          if ($sidebar_responsive.length != 0) {
            $sidebar_responsive.attr('data-color', new_color);
          }
        });

        $('.fixed-plugin .background-color .badge').click(function() {
          $(this).siblings().removeClass('active');
          $(this).addClass('active');

          var new_color = $(this).data('background-color');

          if ($sidebar.length != 0) {
            $sidebar.attr('data-background-color', new_color);
          }
        });

        $('.fixed-plugin .img-holder').click(function() {
          $full_page_background = $('.full-page-background');

          $(this).parent('li').siblings().removeClass('active');
          $(this).parent('li').addClass('active');


          var new_image = $(this).find("img").attr('src');

          if ($sidebar_img_container.length != 0 && $('.switch-sidebar-image input:checked').length != 0) {
            $sidebar_img_container.fadeOut('fast', function() {
              $sidebar_img_container.css('background-image', 'url("' + new_image + '")');
              $sidebar_img_container.fadeIn('fast');
            });
          }

          if ($full_page_background.length != 0 && $('.switch-sidebar-image input:checked').length != 0) {
            var new_image_full_page = $('.fixed-plugin li.active .img-holder').find('img').data('src');

            $full_page_background.fadeOut('fast', function() {
              $full_page_background.css('background-image', 'url("' + new_image_full_page + '")');
              $full_page_background.fadeIn('fast');
            });
          }

          if ($('.switch-sidebar-image input:checked').length == 0) {
            var new_image = $('.fixed-plugin li.active .img-holder').find("img").attr('src');
            var new_image_full_page = $('.fixed-plugin li.active .img-holder').find('img').data('src');

            $sidebar_img_container.css('background-image', 'url("' + new_image + '")');
            $full_page_background.css('background-image', 'url("' + new_image_full_page + '")');
          }

          if ($sidebar_responsive.length != 0) {
            $sidebar_responsive.css('background-image', 'url("' + new_image + '")');
          }
        });

        $('.switch-sidebar-image input').change(function() {
          $full_page_background = $('.full-page-background');

          $input = $(this);

          if ($input.is(':checked')) {
            if ($sidebar_img_container.length != 0) {
              $sidebar_img_container.fadeIn('fast');
              $sidebar.attr('data-image', '#');
            }

            if ($full_page_background.length != 0) {
              $full_page_background.fadeIn('fast');
              $full_page.attr('data-image', '#');
            }

            background_image = true;
          } else {
            if ($sidebar_img_container.length != 0) {
              $sidebar.removeAttr('data-image');
              $sidebar_img_container.fadeOut('fast');
            }

            if ($full_page_background.length != 0) {
              $full_page.removeAttr('data-image', '#');
              $full_page_background.fadeOut('fast');
            }

            background_image = false;
          }
        });

        $('.switch-sidebar-mini input').change(function() {
          $body = $('body');

          $input = $(this);

          if (md.misc.sidebar_mini_active == true) {
            $('body').removeClass('sidebar-mini');
            md.misc.sidebar_mini_active = false;

            $('.sidebar .sidebar-wrapper, .main-panel').perfectScrollbar();

          } else {

            $('.sidebar .sidebar-wrapper, .main-panel').perfectScrollbar('destroy');

            setTimeout(function() {
              $('body').addClass('sidebar-mini');

              md.misc.sidebar_mini_active = true;
            }, 300);
          }

          // we simulate the window Resize so the charts will get updated in realtime.
          var simulateWindowResize = setInterval(function() {
            window.dispatchEvent(new Event('resize'));
          }, 180);

          // we stop the simulation of Window Resize after the animations are completed
          setTimeout(function() {
            clearInterval(simulateWindowResize);
          }, 1000);

        });
      });
    });
  </script>
  <script>
    $(document).ready(function() {
      // Javascript method's body can be found in assets/js/demos.js
      md.initDashboardPageCharts();

    });
  </script>

</html>