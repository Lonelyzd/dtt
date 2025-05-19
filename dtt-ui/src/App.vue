<script>
import Store from '@/store'

export default {
  name: 'App',
  created() {
    debugger
    Store.dispatch("main/getMenu");
    console.log("app加载了");
  },
  methods: {}
}

</script>

<template>
  <div id="app">
    <!-- 需要缓存的视图组件 -->
    <keep-alive>
      <router-view v-if="$route.meta.keepAlive" ref="currRoutePage" :key="$route.path" ></router-view>
    </keep-alive>
    <router-view v-if="!$route.meta.keepAlive" ref="currRoutePage" :key="$route.path" ></router-view>
  </div>
</template>

<style scoped>
header {
  line-height: 1.5;
}

.logo {
  display: block;
  margin: 0 auto 2rem;
}

@media (min-width: 1024px) {
  header {
    display: flex;
    place-items: center;
    padding-right: calc(var(--section-gap) / 2);
  }

  .logo {
    margin: 0 2rem 0 0;
  }

  header .wrapper {
    display: flex;
    place-items: flex-start;
    flex-wrap: wrap;
  }
}
</style>
