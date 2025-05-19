<template>
  <div class="hello">
    <img src="./assets/logo.png">
    <h1>{{ msg }}</h1>
    <router-view></router-view>
    <div>
    </div>
  </div>
</template>
<script>
export default {
  name: 'HelloWorld',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  }
}
</script>