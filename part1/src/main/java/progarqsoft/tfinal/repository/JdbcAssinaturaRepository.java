package progarqsoft.tfinal.repository;

import progarqsoft.tfinal.model.Assinatura;

public class JdbcAssinaturaRepository implements AssinaturaRepository {
    private final DataSource dataSource;

    public JdbcAssinaturaRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(Assinatura assinatura) {
        String sql = "INSERT INTO assinaturas (cliente_id, aplicativo_id, data_validade, ativa) VALUES (?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, assinatura.getClienteId());
            ps.setInt(2, assinatura.getAplicativoId());
            ps.setDate(3, Date.valueOf(assinatura.getDataValidade()));
            ps.setBoolean(4, assinatura.isAtiva());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Assinatura findById(int id) {
        String sql = "SELECT * FROM assinaturas WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Assinatura assinatura = new Assinatura();
                    assinatura.setId(rs.getInt("id"));
                    assinatura.setClienteId(rs.getInt("cliente_id"));
                    assinatura.setAplicativoId(rs.getInt("aplicativo_id"));
                    assinatura.setDataValidade(rs.getDate("data_validade").toLocalDate());
                    assinatura.setAtiva(rs.getBoolean("ativa"));
                    return assinatura;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
