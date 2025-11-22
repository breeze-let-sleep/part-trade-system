import request from "@/utils/request";

//示例请求代码
export const getAdminList = () => {
  request.get('/admins/getTest')
}