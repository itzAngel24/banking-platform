variable "location" {
  type    = string
  default = "eastus"
}

variable "resource_group_name" {
  type    = string
  default = "rg-banking-dev"
}

variable "aks_cluster_name" {
  type    = string
  default = "aks-banking-dev"
}
