import request from "@/utils/request";

/**
 * 获取当前用户的未完成订单列表
 * @param {*} currentPage 当前页
 * @param {*} pageSize 页大小
 * @returns 
 */
export const getPageList = (currentPage, pageSize) => {
  return request.get(`/contracts/getOrders?currentPage=${currentPage}&pageSize=${pageSize}`);
}

/**
 * 获取某个订单的详细信息
 * @param {*} orderId 订单id
 * @returns 
 */
export const getOrderDetail = (orderId) => {
  return request.get(`/contracts/getOrderDetail/${orderId}`);
}

/**
 * 对订单进行签名
 * @param {*} orderId 订单id
 * @param {*} userId 用户id
 * @param {*} value 签名内容
 * @returns 
 */
export const signatureContract = (orderId,value) => {
  return request.put(`/contracts/signature?orderId=${orderId}&value=${value}`);
}