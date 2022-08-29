<template>
  <nar-bar></nar-bar>
  <router-view></router-view>
</template>

<script>
import NarBar from "./components/NarBar.vue";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.min.js";
// import $ from "jquery";

export default {
  components: {
    NarBar,
  },
  setup() {
    // $.ajax({
    //   url: "http://127.0.0.1/user/account/token/",
    //   type: "post",
    //   data: {
    //     username: "tld",
    //     password: "ptld",
    //   },
    //   success(resp) {
    //     console.log(resp);
    //   },
    //   error(resp) {
    //     console.log(resp);
    //   },
    // });
    // $.ajax({
    //   url: "http://127.0.0.1/user/account/info/",
    //   type: "get",
    //   headers: {
    //     Authorization: "Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIwNTdlYzQyOThlZGE0NzcxOTA2MzYzYTc4MDQzZTUyZCIsInN1YiI6IjEiLCJpc3MiOiJzZyIsImlhdCI6MTY1OTE3MzMwNiwiZXhwIjoxNjYwMzgyOTA2fQ.pjykmTX5tDFPchUUU0Uts1VrS2DCyMNuUmit1CwXLsc"
    //   },
    //   success(resp) {
    //     console.log(resp);
    //   },
    //   error(resp) {
    //     console.log(resp)
    //   }
    // });
    // $.ajax({
    //   url: "http://127.0.0.1/user/account/register/",
    //   type: "post",
    //   data: {
    //     username: "tld1",
    //     password: "ptld",
    //     confirmPassword: "ptld"
    //   },
    //   success(resp) {
    //     console.log(resp);
    //   },
    //   error(resp) {
    //     console.log(resp);
    //   }
    // })
  },
};
</script>

<style>
body {
  background-image: url("@/assets/images/background.jpg");
  background-size: cover;
}
</style>
