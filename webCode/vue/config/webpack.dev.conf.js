
before(app)
{
  // 由于请求的referer和host不同，所以前端不能拿到数据，需要后端做一个代理
  //  后端向有数据的服务端发送请求，拿到数据，然后前端在向自己的服务器请求那数据
  //  这里使用axios实现ajax请求：axios是一个基于promise的HTTP库，可以用于浏览器和node.js
  // 在浏览器创建XMLHttpRequest对象，从node.js创建http请求
  app.get('/api/getDiscList', (req, res) => {//这里的路径是给前端发送请求的url
    let url = 'http://localhost'
    // axios发送get请求，可以自己配置config
    axios.get(url, {
      headers: {
        'referer': 'https://c.y.qq.com/',
        'host': 'c.y.qq.com',
      },
      //  params是即将与请求一起发送的url参数，无格式对象/URLSearchParams对象
      params: req.quest
    }).then((response) => {
      res.json(response.data)  //返回数据
    }).catch((error) => {
      console.log(error)
    })
  })
}
