import request from "@/utils/request";

export const getLogs = (currentPage, pageSize) => {
  return request.get(`/logs/getLogs?currentPage=${currentPage}&pageSize=${pageSize}`);
}

