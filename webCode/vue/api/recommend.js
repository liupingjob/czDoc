
import axios from 'axios'

//由于这里使用了代理请求，所以前端的请求要改成ajax请求
export function getDisList() {
  const url = '/api/getDiscList'
  const data = {
    picmid: 1,
    rnd: Math.random(),
    g_tk: 5381,
    jsonpCallback: 'getPlaylist',
    loginUin: '0',
    hostUin: 0,
    format: 'json',
    inCharset: 'utf8',
    outCharse: 'utf-8',
    notice: 0,
    platform: 'yqq',
    needNewCode: 0,
    categoryId: 10000000,
    sortId: 5,
    sin: 0,
    ein: 29
  }
  //使用ajax请求，这里用axios
  return axios.get(url, {
    params: data
  }).then((res) => {
    //  成功返回一个Promise对象
    return Promise.resolve(res.data)
  }).catch((error) => {
    console.log(error)
  })
}
