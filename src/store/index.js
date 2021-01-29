import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    user: {
      username: window.localStorage.getItem('user' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('user' || '[]')).username
    }
  },
  mutations: {
    login (state, user) {
      state.user = user
      window.localStorage.setItem('user', JSON.stringify(user))
      // cookie: 关闭浏览器失效 localStorage：永久保存 sessionStorage：当前会话有效
    },
    logout (state) {
      state.user = []
      window.localStorage.removeItem('user')
    }
  }
})
