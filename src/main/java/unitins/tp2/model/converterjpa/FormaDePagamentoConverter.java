package unitins.tp2.model.converterjpa;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import unitins.tp2.model.FormaDePagamento;

@Converter(autoApply = true)
public class FormaDePagamentoConverter implements AttributeConverter<FormaDePagamento, Integer> {

    @Override
    public Integer convertToDatabaseColumn(FormaDePagamento tp) {
        return (tp == null ? null : tp.getId());
    }

    @Override
    public FormaDePagamento convertToEntityAttribute(Integer id) {
        return FormaDePagamento.valueOf(id);
    }

}
