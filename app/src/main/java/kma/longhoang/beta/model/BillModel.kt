package kma.longhoang.beta.model

import kma.longhoang.beta.BillState
import java.util.*

class BillModel(
    var orderList: MutableList<OrderModel> ?= null,
    var id: Int?= null,
    var customer: CustomerModel?= null,
    var date: Date?= null,
    var state: BillState?= null,
)