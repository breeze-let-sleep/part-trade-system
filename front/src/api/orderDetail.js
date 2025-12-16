import request from "@/utils/request";

export const getProcessingList = (currentPage, pageSize) => {
  return request.get(`/details/getProcessingOrders?currentPage=${currentPage}&pageSize=${pageSize}`);
}

export const getEndList = (currentPage, pageSize) => {
  return request.get(`/details/getEndOrders?currentPage=${currentPage}&pageSize=${pageSize}`);
}

export const takeOrder = (orderDetail) => {
  return request.put(`/details/evaluateOrder`,orderDetail);
}

export const deliverOrder = (id) => {
  return request.put(`/details/deliver/${id}`);
}