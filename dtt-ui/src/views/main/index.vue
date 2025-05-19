<template>
  <div>
    <el-container style="height: 100%">
      <el-header style="height: 48px">
        <Header></Header>
      </el-header>
      <el-container style="height: calc(100% - 48px)">
        <keep-alive>
          <router-view></router-view>
        </keep-alive>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import {mapActions, mapState} from "vuex";
import Header from "@/components/header.vue";

export default {
  name: "index",
  components: {Header},
  data() {
    return {
      collapse: false,
    };
  },
  created() {
    // this['main/getMenu']()
    this.$store.commit("main/updateActive", this.$route.path);
    this.getConfig();
  },
  computed: {
    ...mapState("main", ["tabs"]),
  },
  methods: {
    ...mapActions(["main/getMenu"]),
    getConfig() {
      // this.$store.commit('main/updateConfig',{})
    },
  },
};
</script>

<style lang="scss">
.w-header {
  padding: 0;
}

.asideShort {
  width: 64px !important;
}

.w-main {
  background: #eff2fd;
  padding: 0;
  height: 100%;
}

.app-main {
  flex: 1;
  overflow: auto;
  padding: 12px;
  box-sizing: border-box;
}

.app-el-main {
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 100%;
}

.main-center {
  position: relative;
  display: flex;
  height: 100%;
  flex-direction: column;

  .main-table {
    margin: 0px 0 16px;
    flex: 1;
  }
}

.w-table__body-wrapper {
  height: calc(100% - 40px);
  overflow-y: auto;
}

//.w-table__body-wrapper::-webkit-scrollbar{
//  display: none;
//}
</style>
