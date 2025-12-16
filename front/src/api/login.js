import request from '@/utils/request'

export const getLogin = (userVO) => {
  console.log(userVO)
  return request.post('/login', userVO)
}

export const getRegister = (userVO) => {
  console.log(userVO)
  return request.post('/register', userVO)
}