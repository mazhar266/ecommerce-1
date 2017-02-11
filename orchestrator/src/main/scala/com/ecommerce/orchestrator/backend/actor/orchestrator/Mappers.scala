package com.ecommerce.orchestrator.backend.actor.orchestrator

import com.ecommerce.common.views.InventoryResponse
import com.ecommerce.common.views.ReceivingResponse

/**
  * Created by lukewyman on 2/8/17.
  */

object Mappers {
  import InventoryResponse._
  import ReceivingResponse._
  import ResponseViews._

  def mapToReceivingSummaryView(shipmentView: ShipmentView, inventoryItemView: InventoryItemView): ReceivingSummaryView =
    ReceivingSummaryView(
      shipmentView.productId,
      shipmentView.shipmentId,
      shipmentView.ordered,
      shipmentView.count,
      shipmentView.expectedDelivery,
      shipmentView.delivered,
      inventoryItemView.inStock,
      inventoryItemView.onBackorder
    )

}
