
import Mock from 'mockjs'
import data from './data.json'

// 映射个接口
Mock.mock('/api2/seller', {
  code: 0,
  data: data.number
})


