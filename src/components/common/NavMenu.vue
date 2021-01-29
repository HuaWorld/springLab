<template>
  <el-menu
    :default-active="'/index'"
    router
    mode="horizontal"
    background-color="white"
    text-color="#222"
    active-text-color="red"
    style="min-width: 1300px">
    <el-menu-item v-for="(item,i) in navList" :key="i" :index="item.name">
      {{ item.navItem }}
    </el-menu-item>
    <i class="el-icon-switch-button" v-on:click="logout" style="float:right;font-size: 25px; color: #222; padding: 15px"></i>

    <!--    <el-dropdown style="float: right; margin-top: 20px;">-->
<!--      <i class="el-icon-menu el-icon-arrow-down el-icon&#45;&#45;right" style="float:right;font-size: 35px;color: #222;padding-top: 8px"></i>-->
<!--      <el-dropdown-menu slot="dropdown">-->
<!--        <el-dropdown-item>黄金糕</el-dropdown-item>-->
<!--        <el-dropdown-item>狮子头</el-dropdown-item>-->
<!--        <el-dropdown-item>螺蛳粉</el-dropdown-item>-->
<!--        <el-dropdown-item>双皮奶</el-dropdown-item>-->
<!--        <el-dropdown-item>蚵仔煎</el-dropdown-item>-->
<!--      </el-dropdown-menu>-->
<!--    </el-dropdown>-->
<!--    <i class="el-icon-menu" style="float:right;font-size: 45px;color: #222;padding-top: 8px"></i>-->
    <span style="position: absolute;padding-top: 20px;right: 43%;font-size: 20px;font-weight: bold">XiaoHu Feng - 图书管理</span>
  </el-menu>
</template>

<script>
export default {
  name: 'NavMenu',
  data () {
    return {
      navList: [
        {name: '/index', navItem: '首页'},
        {name: '/jotter', navItem: '笔记本'},
        {name: '/library', navItem: '图书馆'},
        {name: '/admin', navItem: '个人中心'}
      ]
    }
  },
  methods: {
    logout () {
      var _this = this
      this.$axios.get('/logout').then(resp => {
        if (resp.data.code === 200) {
          // 前后端包持一致
          _this.$store.commit('logout')
          _this.$router.replace('/login')
        }
      }
      )
    }
  }
}
</script>

<style scoped>
  a{
    text-decoration: none;
  }
  .el-icon-switch-button {
    cursor: pointer;
    outline:0px;
  }
  span {
    pointer-events: none;
  }
</style>
