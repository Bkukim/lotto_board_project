<template>
  <div>
    <nav class="navbar bg-light fixed-top">
      <div class="container-fluid">
        <a class="navbar-brand" href="#" style="font-weight: bold; color: #162b59; font-size: 50px;">LOTTO ADMIN</a>
        <button
          class="navbar-toggler"
          type="button"
          data-bs-toggle="offcanvas"
          data-bs-target="#offcanvasNavbar"
          aria-controls="offcanvasNavbar"
        >
          <span class="navbar-toggler-icon"></span>
        </button>
        <div
          class="offcanvas offcanvas-end"
          tabindex="-1"
          id="offcanvasNavbar"
          aria-labelledby="offcanvasNavbarLabel"
        >
          <div class="offcanvas-header">
            <h5 class="offcanvas-title" id="offcanvasNavbarLabel" style="font-weight: bold;">lotto Administrator page</h5>
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="offcanvas"
              aria-label="Close"
            ></button>
          </div>
          <div class="offcanvas-body">
            <ul class="navbar-nav justify-content-end flex-grow-1 pe-3 mt-5" style="font-size: 23px; gap: 25px; letter-spacing: -1px;">
              <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="#" style="font-weight: 100;">공지사항 관리</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#">자유게시판 관리</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#">신고 게시글 관리</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#">동아리 게시판 관리</a>
              </li>
              <li class="nav-item dropdown">
                <a
                  class="nav-link dropdown-toggle"
                  href="#"
                  role="button"
                  data-bs-toggle="dropdown"
                  aria-expanded="false"
                >
                  부서게시판 관리
                </a>
                <ul class="dropdown-menu">
                  <li><a class="dropdown-item" href="#">건의사항 관리</a></li>
                  <li><a class="dropdown-item" href="#">Another action</a></li>
                  <li>
                    <hr class="dropdown-divider" />
                  </li>
                  <li>
                    <a class="dropdown-item" href="#">Something else here</a>
                  </li>
                </ul>
              </li>
            </ul>
            <form class="d-flex mt-3" role="search">
              <input
                class="form-control me-2"
                type="search"
                placeholder="Search"
                aria-label="Search"
              />
              <button class="btn btn-outline-success" type="submit" id="s_all">
                Search
              </button>
            </form>
          </div>
        </div>
      </div>
    </nav>
  </div>
</template>
<script>

</script>
<style>
#s_all{
    border: 1px solid #162b59;
    color: #162b59;
}
#s_all:hover {
  background-color: #2d61d056;
  border: none;
}
</style>